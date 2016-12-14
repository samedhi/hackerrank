(ns hackerrank.week-of-code-22.cookie-party)

#_(let [[n m] (->> (clojure.string/split (read-line) #"\s+")
                 (map #(Integer/parseInt %)))
      r (rem m n)
      d (- n r)]
  (println (if (zero? r) 0 d)))
