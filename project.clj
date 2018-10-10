(defproject zeppelin "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring/ring-core "1.7.0"]
                 [ring/ring-devel"1.7.0"]
                 [ring/ring-mock "0.3.2"]
                 [aleph "0.4.6"]
                 [compojure "1.6.1"]
                 [cheshire "5.8.1"]]
  :main ^:skip-aot zeppelin.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
