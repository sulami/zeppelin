(ns zeppelin.utils
  (:require [clojure.string :as str]))

(defn log
  "Log something. Log level defaults to :debug."
  ([msg level]
   (printf "[%s] %s\n" (-> level name str/upper-case) msg)
   (flush))

  ([msg]
   (log msg :debug)))
