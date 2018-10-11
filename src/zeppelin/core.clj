(ns zeppelin.core
  (:gen-class)
  (:require [aleph.http :as http]
            [zeppelin.routes :refer [dev-router]]
            [zeppelin.utils :refer [log]]))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (log "Starting server...")
  (http/start-server dev-router {:port 10000}))
