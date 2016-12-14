(set-env!
 :source-paths #{"src"}
 :dependencies
 '[[adzerk/boot-test                        "1.1.1"]
   [org.clojure/clojure                     "1.9.0-alpha13"]
   [org.clojure/core.logic                  "0.8.11"]
   [org.clojure/test.check                  "0.9.0" :scope "test"]
   [org.clojure/tools.nrepl                 "0.2.12" :scope "test"]])

(require '[adzerk.boot-test      :as boot-test])

(task-options!
 repl      {:port 6800,
            :bind "0.0.0.0"})

(deftask deps [] (repl :server true))

(deftask test
  "Runs all clojure test"
  []
  (boot-test/test))

(deftask autotest
  "Runs test automatically on file change"
  []
  (comp
   (watch)
   (speak)
   (test)))
