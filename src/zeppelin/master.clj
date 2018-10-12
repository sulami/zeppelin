(ns zeppelin.master
  (:require [clojure.core.async :refer [>!! chan go]]))

;; TODO spec this
(def initial-master-state
  "Initial master state upon boot.
  :builds is a map of build ids to build maps"
  {:builds {}})

(defonce master-state
  (atom initial-master-state))

(defn new-build
  "Construct a new build map."
  [state params]
  (let [next-id (->> state
                     :builds
                     keys
                     (concat [0])
                     (apply max)
                     inc)]
    [next-id (into params
                   {:id next-id
                    :state :running})]))

(defn start-build
  "Kick off a build."
  ([state params]
   (let [[id build] (new-build state params)]
     (update-in state [:builds] #(assoc % id build))))

  ([state]
   (start-build state {})))

(defn finish-build
  "Finish a build, setting its :state to :finished"
  [state id]
  (if (-> state :builds (contains? id))
    (update-in state [:builds id :state] (constantly :finished))
    state))

(defn query-master
  "Run a query on the master.
  Returns a channel which will eventually contain the status."
  [cmd & args]
  (case cmd
    :echo (go args)
    :status (go @master-state)
    (go :invalid-command)))
