(ns zeppelin.core-test
  (:require [clojure.test :refer :all]
            [zeppelin.core :refer :all]))

(deftest sanity-test
  (testing "it is sane"
    (is (= 1 1))))
