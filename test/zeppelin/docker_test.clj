(ns zeppelin.docker-test
  (:require [zeppelin.docker :refer :all]
            [clojure.test :refer :all]))

(def docker-command-prefix
  "This is the standard docker run command prefix."
  ["docker" "run" "--rm"])

(deftest docker-run-cmd-test
  (testing "it prefixes the command properly"
    (is (= ["docker" "run" "--rm" "alpine:3.7" "echo" "-n" "docker"]
           (docker-run-cmd "alpine:3.7" ["echo" "-n" "docker"])))))

(deftest run-container-test
  (testing "it runs a container and returns the output"
    (with-redefs-fn
      {#'run-command
       (fn run-command-mock [& cmd]
         (is (= (concat docker-command-prefix ["image" "command"])
                cmd))
         (future {:out "docker"}))}
      #(is (= "docker"
             (-> (run-container "image" ["command"])
                 deref
                 :out))))))
