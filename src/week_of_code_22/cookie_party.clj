(ns hackerrank.week-of-code22.cookie-party)

(let [[n m] (->> (clojure.string/split (read-line) #"\s+")
                 (map #(Integer/parseInt %)))
      r (rem m n)
      d (- n r)]
  (println (if (zero? r) 0 d)))
