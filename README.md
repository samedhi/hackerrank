# HackerRank

## Synopsis
Problems I have worked upon on [HackerRank](hackerrank.com). All solutions are in Clojure. 

## Code Example
Each problem is usually in 1 file (namespace). I usually divide up a problem into 3 parts. Here is an example.
```clojure
(ns hackerrank.challenges.two-strings
  (:require
   [clojure.set :refer [intersection]]
   [clojure.test :as test :refer [deftest testing is]]))

;; PART 1 - functionality being developed.
(defn string-intersection? [s1 s2]
  (-> (intersection (set s1) (set s2))
      empty?
      not))

;; PART 2 - define some simple to complex test.
(deftest two-strings-test []
  (is (= true (string-intersection? "hello" "world")))
  (is (= false (string-intersection? "hi" "world"))))

;; PART 3 - submission script that reads from stdin and writes to stdout. 
;; I comment them out in the code using `#_` in order that they not block
;; my test runner from executing.
#_(doseq [i (range (Integer/parseInt (read-line)))
          :let [s1 (read-line)
                s2 (read-line)]]
    (println
     (if (string-intersection? s1 s2)
       "YES"
       "NO")))
```

## Use
You will need to have [boot installed](https://github.com/boot-clj/boot#install).

```bash
> git clone git@github.com:samedhi/hackerrank.git
> cd hackerrank
> boot autotest
```
