(ns zeppelin.middleware-test
  (:require [zeppelin.middleware :refer :all]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :refer [parse-string]]))

(def mock-response
  {:status 200
   :body {:status "OK"}})

(defn mock-handler
  [req]
  mock-response)

(deftest wrap-json-response-test
  (let [handler (wrap-json-response mock-handler)
        req (mock/request :get "/status")]

    (testing "it sets the content-type header"
      (is (= '(["content-type" "application/json"])
             (-> req handler :headers))))

    (testing "it encodess the response body"
      (is (= (-> mock-response :body)
             (-> req handler :body (parse-string true)))))))
