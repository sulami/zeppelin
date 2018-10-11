(ns zeppelin.master-test
  (:require [zeppelin.master :refer :all]
            [clojure.test :refer :all]
            [clojure.core.async :as async :refer [<!!]]))

(deftest query-master-test
  (testing "it refutes invalid commands"
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
