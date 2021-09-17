(defproject xyz.thoren/julian "1.0.2-SNAPSHOT"
  :description "Convert between Julian and Gregorian days."
  :url "https://github.com/johanthoren/julian"
  :license {:name "ISC"
            :url "https://choosealicense.com/licenses/isc"
            :comment "ISC License"
            :year 2021
            :key "isc"}
  :min-lein-version "2.5.2"
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [org.clojure/clojurescript "1.10.879"]]
  :repl-options {:init-ns xyz.thoren.julian
                 :nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
  :profiles {:dev {:dependencies [[cider/piggieback "0.4.2"]]}}
  :repositories [["releases" {:url "https://repo.clojars.org"
                              :creds :gpg}]]
  :release-tasks [["test"]
                  ["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  ["vcs" "commit"]
                  ["vcs" "tag"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  ["vcs" "commit"]
                  ["vcs" "push"]])
