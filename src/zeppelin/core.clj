(ns zeppelin.core
  (:gen-class)
  (:require [aleph.http :as http]
            [zeppelin.routes :refer [dev-router]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Starting server...")
  (http/start-server dev-router {:port 10000}))
