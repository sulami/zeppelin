(ns zeppelin.routes
  (:require [compojure.core :as compojure :refer [GET]]
            [compojure.route :as route]
            [zeppelin.handlers :as handlers]))

(def router
  (compojure/routes
   (GET "/status" [] (handlers/json-handler handlers/status-handler))
   (route/not-found (handlers/json-handler handlers/not-found-handler))))