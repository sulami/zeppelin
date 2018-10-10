(ns zeppelin.docker
  (:require [clojure.java.shell :refer [sh]]))

(defn run-command
  "Asynchronously run a command, returning a future of the results."
  [& cmd]
  (future
    (apply sh cmd)))
