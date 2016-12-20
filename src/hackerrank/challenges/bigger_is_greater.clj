(ns hackerrank.challenges.bigger-is-greater
  (:require
   [clojure.string :refer [join]]
   [clojure.test :as test :refer [deftest testing is]]))

(defn add-sorted [ls v]
  (loop [acc []
         [l :as ls] ls]
    #_(println acc ls)
    (if (or (empty? ls) (<= (compare v l) 0))
      (concat acc [v] ls)
      (recur (conj acc l) (rest ls)))))

(defn next-permutation [input]
  {:pre [(vector? input)]}
  (loop [xs (pop input)
         y  (peek input)
         zs '()]
    (let [[smaller-or-equal larger] (split-with #(<= (compare % y) 0) zs)]
      #_(println xs y zs smaller-or-equal larger)
      (if (empty? larger)
        (when-not (empty? xs)
          (recur (pop xs) (peek xs) (add-sorted zs y)))
        (concat xs [(first larger)] smaller-or-equal [y] (rest larger))))))

(defn bigger [s]
  (if-let [output (-> s vec next-permutation)]
    (join "" output)
    "no answer"))

(deftest bigger-is-greater-test []
  (is (= "no answer" (bigger "a")))
  (is (= "ba" (bigger "ab")))
  (is (= "no answer" (bigger "bb")))
  (is (= "hegf" (bigger "hefg")))
  (is (= "dhkc" (bigger "dhck")))
  (is (= "hcdk" (bigger "dkhc")))
  (is (= "baaa" (bigger "abaa")))
  (is (= "no answer" (bigger "baa")))
  (is (= "987123456" (bigger "986754321")))
  )

#_(doseq [i (range (Integer/parseInt (read-line)))
          :let [s (read-line)]]
    (-> s bigger println))
