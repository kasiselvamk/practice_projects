// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

/**
 * @fileoverview An example WebDriver script using generator functions.
 *
 * Before running this script, ensure that Mozilla's geckodriver is present on
 * your system PATH: <https://github.com/mozilla/geckodriver/releases>
 *
 * Usage:
 *
 *     node selenium-webdriver/example/google_search_generator.js
 */

'use strict';

const { Builder, By, promise, until } = require('selenium-webdriver');
const screenshot = require('desktop-screenshot');
const test = require('selenium-webdriver/testing');
const fs = require('fs');
const assert = require('assert');


promise.consume(function* () {
  let driver;
  try {

    driver = yield new Builder().forBrowser('chrome').build();
    driver.manage().window().maximize();

    yield driver.get('https://');//Url...

    let q = yield driver.findElement(By.name('USER'));
    yield q.sendKeys(' ');//User id..

    let a = yield driver.findElement(By.name('PASSWORD'));

    let a1 = yield driver.findElement(By.name('viewPass'));
    yield a1.click();

    yield a.sendKeys(' ');//Password...
    let btnG = yield driver.findElement(By.id('buttonSave'));
    yield btnG.click();

    let agg = yield driver.wait(until.ableToSwitchToFrame(By.id('confidentialityFrame')), 10000);


    let btnAccept = yield driver.findElement(By.id('btnAccept')); //btnAccept
    yield btnAccept.click();
    let isConnected = false;
    driver.switchTo().defaultContent();
    let main_content = yield driver.wait(until.ableToSwitchToFrame(By.id('main_content')), 30000);
    let signal_strength = yield driver.findElement(By.id('signal_strength'));
    console.log(yield signal_strength.getAttribute("class"));

    //   let signal_strength = yield driver.findElement(By.id('signal_strength'));
    //   yield signal_strength.click();
    /*  while (!isConnected){
        try {
          let signal_strength = yield driver.wait(until.elementLocated(By.id('signal_strength')), 10000);
          console.log( signal_strength.getCssValue() );
           isConnected = true;
        } catch (error) {
             console.log(error);
        }
      } */
    console.log("DONE!")
    // yield driver.wait(until.titleIs('webdriver - Google Search'), 2000);
  } finally {

    //screenshot("screenshot.png", function(error, complete) {} );
    driver.takeScreenshot().then(function (data) {
      var base64Data = data.replace(/^data:image\/png;base64,/, "")
      fs.writeFile("out.png", base64Data, 'base64', function (err) {
        if (err) console.log(err);
      });
    });

    //yield driver && driver.quit();
  }
}).then(_ => console.log('SUCCESS'), err => console.error('ERROR: ' + err));
