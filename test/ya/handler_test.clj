(ns ya.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            
            [ya.handler :refer :all]))

(deftest test-app
  (testing "index route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "ya works"))))

  (comment
  (testing "twitter login"
    (let [response (app (mock/request :get "/api/auth/twitter/login"))
          location (get-in response [:headers "Location"])]
      (is (= (:status response) 302))
      (is (.contains location "twitter.com")))))
  
  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
