(ns zeppelin.handlers)

(defn not-found-handler
  [req]
  {:status 404
   :headers {"content-type" "text/plain"}
   :body "Not found"})

(defn status-handler
  [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "OK"})
