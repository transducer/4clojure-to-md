(defproject foreclojure-to-md "1.0.0"
  :description "Scrapes your 4clojure solutions into a Markdown string"
  :license {:name "MIT"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit "2.3.0"]
                 [hiccup "1.0.5"]
                 [hickory "0.7.1"]]
  :main ^:skip-aot foreclojure-to-md.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
