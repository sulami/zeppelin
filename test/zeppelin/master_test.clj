(ns zeppelin.master-test
  (:require [zeppelin.master :refer :all]
            [clojure.test :refer :all]
            [clojure.core.async :as async :refer [<!!]]))

(deftest query-master-test
  (testing "it rejects invalid commands"
    (is (= :invalid-command
           (<!! (query-master :something-invalid)))))

  (testing "it echoes echo requests"
    (is (= ["zeppelin" "is" "cool"]
           (->> [:echo "zeppelin" "is" "cool"]
                (apply query-master)
                <!!))))

  (testing "it returns the status"
    (is (= @master-state
           (<!! (query-master :status))))))

(deftest start-build-test
  (testing "it adds a build to the state"
    (is (= 1
           (-> initial-master-state
               (start-build {})
               :builds
               count))))

  (testing "it adds parameters to the new build"
    (let [params {:id 1}]
      (is (= params
             (-> initial-master-state
                 (start-build params)
                 :builds
                 first))))))
