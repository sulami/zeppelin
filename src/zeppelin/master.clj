(ns zeppelin.master
  (:require [clojure.core.async :refer [>!! chan go]]))

;; Initial master state upon boot.
(defonce master-state
  (atom {}))

(defn query-master
  "Run a query on the master.
  Returns a channel which will eventually contain the status."
  [cmd & args]
  (case cmd
    :echo (go args)
    :status (go @master-state)
    (go :invalid-command)))
