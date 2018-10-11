(ns zeppelin.handlers-test
  (:require [zeppelin.handlers :refer :all]
            [clojure.test :refer :all]
            [ring.mock.request :as mock]))

(deftest not-found-handler-test
  (testing "it 404s"
    (is (= 404
           (-> (mock/request :get "/not-found")
               not-found-handler
               :status)))))

(deftest status-handler-test
  (let [resp (status-handler (mock/request :get "/status"))]
    (testing "it 200s"
      (is (= 200
             (:status resp))))

    (testing "it contains the status"
      (is (= "OK"
             (-> resp :body :status))))))
