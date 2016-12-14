(ns hackerrank.challenges.funny-string
  (:require
   [clojure.test :as test :refer [deftest testing is]])
  (:import
   [java.lang Math]))

(defn funny? [s]
  (let [c (count s)
        n (dec c)]
    (loop [i 1]
      (if (= i c)
        true
        (let [si (->> i (nth s) int)
              si-1 (->> i dec (nth s) int)
              ri (->> i (- c) (nth s) int)
              ri-1 (->> i inc (- c) (nth s) int)]
          (if (= (Math/abs (- si si-1)) (Math/abs (- ri-1 ri)))
            (recur (inc i))
            false))))))

(deftest example-test []
  (is (= true (funny? "acxz")))
  (is (= false (funny? "bcxz"))))

#_(doseq [i (range (Integer/parseInt (read-line)))
          :let [s (read-line)]]
    (println
     (if (funny? s)
       "Funny"
       "Not Funny")))
