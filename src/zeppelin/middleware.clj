(ns zeppelin.middleware
  (:require [cheshire.core :refer [generate-string]]))

(defn wrap-json-response
  "Encode a response body in JSON and set the content-type."
  [handler]
  (fn wrap-json-response-wrapper [req]
    (-> req
        handler
        (update-in [:body] generate-string)
        (update-in [:headers] #(into % {"content-type" "application/json"})))))

(defn- format-request
  "Pretty print a request."
  [req]
  (format "%s %s"
          (:request-method req)
          (:uri req)))

(defn wrap-log-request
  "Log all incoming requests to stdout."
  [handler]
  (fn wrap-log-request-wrapper [req]
    (-> req format-request println)
    (handler req)))
