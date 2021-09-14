(ns xyz.thoren.julian
  #?(:cljs (:require [cljs.reader :as reader]
                     [goog.string :as gstring]
                     goog.string.format)))

(defn- five-decimal-float
  [f]
  {:pre [(float? f)]}
  #?(:cljs (reader/read-string (gstring/format "%.5f" f))
     :clj (read-string (format "%.5f" f))))

(defn to-gregorian
  "Given a Julian Day Number `jdn`, return a map with the Gregorian time. Will
  round `jdn` to 5 decimals."
  [jdn]
  {:pre [(number? jdn)]}
  (let [julian (+ 0.5 (five-decimal-float jdn))
        z (int (Math/floor julian))
        f (- julian (int (Math/floor julian)))
        a (if (< z 2299161)
            z
            (let [alpha (int (Math/floor (/ (- z 1867216.25) 36524.25)))]
              (+ z 1 (- alpha (int (Math/floor (/ alpha 4)))))))
        b (+ a 1524)
        c (int (Math/floor (/ (- b 122.1) 365.25)))
        d (int (Math/floor (* 365.25 c)))
        e (int (Math/floor (/ (- b d) 30.6)))
        month (if (< e 14)
                (dec e)
                (- e 13))
        year (if (> month 2)
               (- c 4716)
               (- c 4715))
        day-with-decimal (->> (* 30.6001 e)
                              (Math/floor)
                              (int)
                              (- b d)
                              (+ f))
        day (int (Math/floor day-with-decimal))
        fraction-of-day (- day-with-decimal day)
        hour-with-decimal (* 24 fraction-of-day)
        hour (int (Math/floor hour-with-decimal))
        fraction-of-hour (five-decimal-float (- hour-with-decimal hour))
        minute-with-decimal (* 60 fraction-of-hour)
        minute (int (Math/floor minute-with-decimal))
        fraction-of-minute (+ 0.01 (- minute-with-decimal minute))
        second (int (Math/floor (* 60 fraction-of-minute)))]
    {:year year
     :month month
     :day day
     :hour hour
     :minute minute
     :second second}))

(defn to-julian
  "Given `:year`, `:month`, `:day`, `:hour`, `:minute`, `:second`, return a
  Julian Day Number with 5 decimal precision."
  [& {:keys [year month day hour minute second]}]
  (let [y (if (> month 2) year (dec year))
        m (if (> month 2) month (+ month 12))
        a (int (Math/floor (/ y 100)))
        b (if (or (> year 1582)
                  (and (= year 1582) (> month 10))
                  (and (= year 1582) (= month 10) (> day 14)))
            (+ (- 2 a) (int (Math/floor (/ a 4))))
            0)
        c (- b 1524.5)
        x (int (Math/floor (* 365.25 (+ y 4716))))
        z (int (Math/floor (* 30.6001 (inc m))))
        f (+ second (* minute 60) (* hour 3600))
        d (+ (float day) (if (zero? f) 0 (/ f 86400)))]
    (five-decimal-float (+ x z c d))))
