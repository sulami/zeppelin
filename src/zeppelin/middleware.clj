(ns zeppelin.middleware
  (:require [cheshire.core :refer [generate-string]]))

(defn wrap-json-response
  "Encode a response body in JSON and set the content-type."
  [handler]
  (fn wrap-json-response-wrapper [req]
    (-> req
        handler
        (update-in [:body] #(generate-string % {:pretty true}))
        (update-in [:headers] #(into % {"content-type" "application/json"})))))

(defn- format-request
  "Pretty print a request & response."
  [req resp]
  (format "%s %s %s"
          (:request-method req)
          (:uri req)
          (:status resp)))

(defn wrap-log-request
  "Log all incoming requests to stdout."
  [handler]
  (fn wrap-log-request-wrapper [req]
    (let [resp (handler req)]
      (println (format-request req resp))
      (handler req))))
