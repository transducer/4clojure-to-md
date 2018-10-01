(ns foreclojure-to-md.core
  "Core namespace for formatting your 4clojure.com solutions to a markdown
  string."
  (:require [hickory.core :as hickory]
            [hickory.select :as select]
            [org.httpkit.client :as http])
  (:gen-class))


(defn foreclojure-url [problem-number]
  (str "http://www.4clojure.com/problem/" problem-number))


(defn get-site-tree
  "Obtains the site tree using a 4clojure.com ring session-id cookie value and
  the problem number."
  [session-id problem-number]
  (let [url       (foreclojure-url problem-number)
        site-tree (-> @(http/get url {:headers {"Cookie" (str "ring-session=" session-id)}})
                      :body
                      hickory/parse
                      hickory/as-hickory)]
    site-tree))


(defn problem-number
  "Obtains the problem number from the Hickory site tree."
  [site-tree]
  (-> (select/select (select/id :prob-number) site-tree)
      first
      :content
      first))


(defn problem-title
  "Obtains the problem title from the Hickory site tree."
  [site-tree]
  (-> (select/select (select/id :prob-title) site-tree)
      first
      :content
      first))


(defn difficulty
  "Obtains the difficulty from the Hickory site tree."
  [site-tree]
  (-> (select/select (select/descendant (select/id :tags) (select/tag "td")) site-tree)
      second
      :content
      first))


(defn code-box-content
  "Gets the code box content (keeps original formatting) from the Hickory site tree."
  [site-tree]
  (-> (select/select (select/id :code-box) site-tree)
      first
      :content
      first))


(defn tests
  "Creates a string with the list of tests for the problem."
  [site-tree]
  (->> (select/select (select/class "test") site-tree)
       (mapcat :content)
       (interpose "\n")
       (apply str)))


(defn format-problem
  "Formats a Hickory site tree into a markdown string of the problem link and
  title description and the problem solution."
  [site-tree]
  (let [url              (foreclojure-url (subs (problem-number site-tree) 1))
        prob-header-link (str "["
                              (problem-number site-tree)
                              " "
                              (problem-title site-tree)
                              " "
                              "[" (difficulty site-tree) "]"
                              "]"
                              "(" url ")")
        code-box         (code-box-content site-tree)]
    (str prob-header-link
         "\n\n```\n"
         (if (empty? code-box)
           "TODO"
           code-box)
         "\n```\n\n")))


(defn -main [session-id]
  (println (str "_Markdown 4clojure solutions generated with_ "
                "[_4clojure-to-md_](https://www.github.com/transducer/4clojure-to-md)"
                "\n\n"))
  (doseq [problem-number (range 1 161)]
    (-> (get-site-tree session-id problem-number)
        format-problem
        println)))
