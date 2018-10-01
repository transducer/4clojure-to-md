(ns foreclojure-to-md.core-test
  (:require [clojure.edn :as edn]
            [clojure.test :refer :all]
            [foreclojure-to-md.core :refer :all]))


(def site-tree
  (edn/read-string (slurp "resources/test_data/problem1_site_tree.edn")))


(deftest problem-number-test
  (is (= (problem-number site-tree) "#1")))


(deftest difficulty-test
  (is (= (difficulty site-tree) "Elementary")))
