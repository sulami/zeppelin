(ns zeppelin.docker-test
  (:require [zeppelin.docker :refer :all]
            [clojure.test :refer :all]))

(deftest run-command-test
  (testing "it returns a promise"
    (is (future? (run-command "true"))))

  (testing "it returns the correct return code"
    (is (= 0 (-> "true" run-command deref :exit)))
    (is (= 1 (-> "false" run-command deref :exit))))

  (testing "it returns the correct output"
    (is (= "zeppelin"
           (->> ["echo" "-n" "zeppelin"]
                (apply run-command)
                deref
                :out)))))
