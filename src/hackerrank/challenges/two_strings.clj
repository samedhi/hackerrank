(ns hackerrank.challenges.two-strings
  (:require
   [clojure.set :refer [intersection]]
   [clojure.test :as test :refer [deftest testing is]]))

(defn string-intersection? [s1 s2]
  (-> (intersection (set s1) (set s2))
      empty?
      not))

(deftest two-strings-test []
  (is (= true (string-intersection? "hello" "world")))
  (is (= false (string-intersection? "hi" "world"))))

#_(doseq [i (range (Integer/parseInt (read-line)))
          :let [s1 (read-line)
                s2 (read-line)]]
    (println
     (if (string-intersection? s1 s2)
       "YES"
       "NO")))
