(ns xyz.thoren.julian-test
  (:require [clojure.test :refer [deftest is testing]]
            [xyz.thoren.julian :refer [to-gregorian to-julian]]))

(def jean-meeus-examples-p62
  [[2000 1 1 12 0 0 2451545.0]
   [1999 1 1 0 0 0 2451179.5]
   [1987 1 27 0 0 0 2446822.5]
   [1987 6 19 12 0 0 2446966.0]
   [1988 1 27 0 0 0 2447187.5]
   [1988 6 19 12 0 0 2447332.0]
   [1900 1 1 0 0 0 2415020.5]
   [1600 1 1 0 0 0 2305447.5]
   [1600 12 31 0 0 0 2305812.5]
   [837 4 10 7 12 0 2026871.8]
   [-123 12 31 0 0 0 1676496.5]
   [-122 1 1 0 0 0 1676497.5]
   [-1000 7 12 12 0 0 1356001.0]
   [-1001 8 17 21 36 0 1355671.4]
   [-4712 1 1 12 0 0 0.0]])

(def rounding-examples
  [[2021 3 20 0 0 8 2459293.50009]
   [2021 3 20 0 0 9 2459293.50010]])

(def float-examples
  [[1.12345499999 1.12345]
   [0.1 0.1]
   [1.0 1.0]
   [0.000009 0.00001]
   [0.000004 0.0]])

(defn- doseq-tests [f coll] (doseq [x coll] (apply f x)))

(deftest convert-to-gregorian
  (let [t #(is (= {:year %1 :month %2 :day %3 :hour %4 :minute %5 :second %6}
                  (to-gregorian %7)))]
    (testing "the reverse examples from Jean Meeus Astronomical Algorithms p.62"
      (doseq-tests t jean-meeus-examples-p62))
    (testing "rounding"
      (doseq-tests t rounding-examples))))

(deftest convert-to-julian
  (let [t #(is (= %7 (to-julian :year %1
                                :month %2
                                :day %3
                                :hour %4
                                :minute %5
                                :second %6)))]
    (testing "the examples from Jean Meeus Astronomical Algorithms p.62"
      (doseq-tests t jean-meeus-examples-p62))
    (testing "rounding"
      (doseq-tests t rounding-examples))))

(deftest test-five-decimal-float
  (testing "that numbers are correctly truncated"
    (let [t #(is (= %2 (#'xyz.thoren.julian/five-decimal-float %1)))]
      (doseq-tests t float-examples))))