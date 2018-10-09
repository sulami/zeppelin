(ns zeppelin.core
  (:gen-class)
  (:require [aleph.http :as http]
            [compojure.core :as compojure :refer [GET]]
            [compojure.route :as route]))

(defn status-handler
  [req]
  {:status 200
   :headers {"content-type" "text/plain"}
   :body "OK"})

(def router
  (compojure/routes
   (GET "/status" [] status-handler)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Starting server...")
  (http/start-server router {:port 10000}))
