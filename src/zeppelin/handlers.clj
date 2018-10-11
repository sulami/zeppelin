(ns zeppelin.handlers
  (:require [zeppelin.master :refer [master-state]]))

(defn not-found-handler
  [req]
  {:status 404
   :body {:status "Not found"}})

(defn status-handler
  [req]
  {:status 200
   :body {:status "OK"
          :master @master-state}})
