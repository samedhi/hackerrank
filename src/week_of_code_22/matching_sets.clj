(ns hackerrank.week-of-code22.matching-sets)

(letfn [(seq-intersection [xs ys]
          (loop [[x & xs :as xso] xs
                 [y & ys :as yso] ys
                 acc []]
            (cond
              (or (nil? x) (nil? y)) acc
              (< x y) (recur xs yso acc)
              (> x y) (recur xso ys acc)
              :else (recur xs ys (conj acc x)))))
        (remove-sub-xs [xs sub-xs]
          (loop [[x & xs :as xso] xs
                 [r & rs :as rso] sub-xs
                 acc []]
            (cond
              (nil? r) (reduce conj acc xso)
              (< x r) (recur xs rso (conj acc x))
              :else (recur xs rs acc))))]
  (read-line)
  (let [xs (->> (clojure.string/split (read-line) #"\s+") (map #(Integer/parseInt %)) (map long) sort)
        ys (->> (clojure.string/split (read-line) #"\s+") (map #(Integer/parseInt %)) (map long) sort)]
    (if (not= (apply + xs) (apply + ys))
      (println -1)
      (loop [xs xs
             ys ys
             c 0]
        (if (= xs ys)
          (println c)
          (let [is (seq-intersection xs ys)
                [fx :as nxs] (remove-sub-xs xs is)
                [fy :as nys] (remove-sub-xs ys is)
                lx (last nxs)
                ly (last nys)
                df (- fy fx)
                dl (- lx ly)
                d (if (<= (Math/abs df) (Math/abs dl)) df dl)
                uxs (-> nxs (update-in [0] + d) (update-in [(-> nxs count dec)] - d))]
            (recur (sort uxs) nys (+ c (Math/abs d)))))))))
