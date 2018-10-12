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
               start-build
               :builds
               count))))

  (testing "it adds parameters to the new build"
    (is (= "zeppelin"
           (-> initial-master-state
               (start-build {:image "zeppelin"})
               :builds
               (get 1)
               :image))))

  (testing "it increments the id for every new build"
    (is (= [1 2]
           (-> initial-master-state
               start-build
               start-build
               :builds
               keys))))

  (testing "it sets the build state to :running"
    (is (= :running
           (-> initial-master-state
               start-build
               :builds
               (get 1)
               :state)))))

(deftest finish-build-test
  (testing "it requires the build to exist"
    (is (= initial-master-state
           (-> initial-master-state
               (finish-build 1)))))

  (testing "it updates the build state"
    (let [state (-> initial-master-state
                    start-build
                    (finish-build 1))]
      (is (= :finished
             (-> state
                 :builds
                 (get 1)
                 :state))))))
