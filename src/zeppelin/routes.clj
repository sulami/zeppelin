(ns zeppelin.routes
  (:require [compojure.core :as compojure :refer [GET]]
            [compojure.route :as route]
            [ring.middleware.reload :refer [wrap-reload]]
            [zeppelin.handlers :as handlers]
            [zeppelin.middleware :as middleware]))

(defn apply-middleware
  "Add the whole middleware stack to a handler."
  [handler]
  (-> handler
      middleware/wrap-log-request
      middleware/wrap-json-response))

(def router
  (compojure/routes
   (GET "/status" [] (apply-middleware handlers/status-handler))
   (route/not-found (apply-middleware handlers/not-found-handler))))

(def dev-router
  (wrap-reload #'router))
