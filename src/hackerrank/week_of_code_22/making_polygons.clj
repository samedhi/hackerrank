(ns hackerrank.week-of-code-22.making-polygons)

#_(let [_ (read-line) ;; Ignored
      [a & ar :as ax] (->> (clojure.string/split (read-line) #"\s+")
                           (map #(Integer/parseInt %))
                           sort
                           reverse)]
  (println
   (cond
     (-> ax count (= 1)) 2
     (-> ax count (= 2)) 1
     (< a (apply + ar)) 0
     :else 1)))
