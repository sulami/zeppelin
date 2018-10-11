(ns zeppelin.master
  (:require [clojure.core.async :refer [>!! chan go]]))

;; TODO spec this
(def initial-master-state
  "Initial master state upon boot."
  {:builds []})

(defn new-build
  "New build state when added."
  [params]
  (into {} params))

(defonce master-state
  (atom initial-master-state))

(defn start-build
  "Kick off a build."
  [state params]
  (update-in state [:builds] #(conj % (new-build params))))

(defn query-master
  "Run a query on the master.
  Returns a channel which will eventually contain the status."
  [cmd & args]
  (case cmd
    :echo (go args)
    :status (go @master-state)
    (go :invalid-command)))
