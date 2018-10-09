(ns zeppelin.routes
  (:require [compojure.core :as compojure :refer [GET]]
            [compojure.route :as route]
            [zeppelin.handlers :as handlers]))

(def router
  (compojure/routes
   (GET "/status" [] handlers/status-handler)
   (route/not-found handlers/not-found-handler)))
