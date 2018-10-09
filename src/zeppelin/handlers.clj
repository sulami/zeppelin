(ns zeppelin.handlers
  (:require [cheshire.core :refer [generate-string]]))

(defn json-handler
  "Encode a response body in JSON and set the content-type."
  [handler]
  (fn json-handler-wrapper [req]
    (-> req
        handler
        (update-in [:body] generate-string)
        (update-in [:headers] #(into % {"content-type" "application/json"})))))

(defn not-found-handler
  [req]
  {:status 404
   :body {:status "Not found"}})

(defn status-handler
  [req]
  {:status 200
   :body {:status "OK"}})
