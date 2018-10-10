(ns zeppelin.docker
  (:require [clojure.java.shell :refer [sh]]))

(defn run-command
  "Asynchronously run a command, returning a future of the results."
  [& cmd]
  (future
    (apply sh cmd)))

(defn docker-run-cmd
  "Construct the `docker run` command."
  [image cmd]
  (concat ["docker" "run" "--rm" image] cmd))

(defn run-container
  ""
  [image cmd]
  (->> [image cmd]
       (apply docker-run-cmd)
       (apply run-command)))
