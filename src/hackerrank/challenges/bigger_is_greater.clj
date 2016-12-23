(ns hackerrank.challenges.bigger-is-greater
  (:require
   [clojure.spec :as s]
   [clojure.spec.gen :as gen]
   [clojure.spec.test :as stest]
   [clojure.string :refer [join]]
   [clojure.test :as test :refer [deftest testing is]]))

(defn next-permutation [input]
  {:pre [(vector? input)]}
  (loop [xs (pop input)
         y  (peek input)
         zs '()]
    (let [[smaller-or-equal larger] (split-with #(<= (compare % y) 0) zs)]
      #_(println xs y zs smaller-or-equal larger)
      (if (empty? larger)
        (when-not (empty? xs)
          (recur (pop xs) (peek xs) (-> smaller-or-equal (conj y) sort)))
        (concat xs [(first larger)] smaller-or-equal [y] (rest larger))))))

(defn bigger [s]
  (when-let [r (-> s vec next-permutation)]
    (join "" r)))

#_(doseq [i (range (Integer/parseInt (read-line)))
          :let [s (read-line)]]
    (-> s bigger (or "no answer") println))

(s/def ::pos-int (s/and int? #(> % 0)))
(s/def ::pos-int-str (gen/fmap #(str (if ((complement pos?) %) (-> % (* -1) inc) %)) (s/gen int?)))
(s/def ::pos-int-str (s/with-gen (s/and string? #(-> % count pos?))
                       #(gen/fmap str (s/gen ::pos-int))))

(s/fdef bigger
        :args (s/cat :s ::pos-int-str)
        :ret (s/nilable ::pos-int-str)
        :fn
        (s/or
         :nonexistent
         (fn [{:keys [args ret]}]
           (and (nil? ret)
                (let [{:keys [s]} args]
                  (every?
                   (complement neg?)
                   (map #(- (int %1) (int %2)) s (rest s))))))
         :ok
         (fn [{:keys [args ret]}]
           (let [{:keys [s]} args
                 [start end] (map #(BigInteger. %) [s ret])
                 between (range (inc start) end)
                 expected (frequencies s)]
             (every?
              #(not= expected %)
              (map #(-> % str frequencies) between))))))

;; (stest/check `bigger)

(deftest bigger-is-greater-test []
  (is (= nil (bigger "a")))
  (is (= "ba" (bigger "ab")))
  (is (= nil (bigger "bb")))
  (is (= "hegf" (bigger "hefg")))
  (is (= "dhkc" (bigger "dhck")))
  (is (= "hcdk" (bigger "dkhc")))
  (is (= "baaa" (bigger "abaa")))
  (is (= nil (bigger "321")))
  (is (= "987123456" (bigger "986754321")))
  )
