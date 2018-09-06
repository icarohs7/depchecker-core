/*
 *  MIT License
 *
 * Copyright (c) 2018.  Icaro R D Temponi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.icarohs7.persistence

import com.github.icarohs7.entities.Artifact
import io.kotlintest.*
import io.kotlintest.specs.StringSpec

class ArtifactRamDatabaseTest : StringSpec() {
    override fun isInstancePerTest(): Boolean {
        return true
    }
    
    override fun beforeTest(description: Description) {
        ArtifactDao.getInMemoryDatabaseInstance().removeAll()
    }
    
    init {
        "should persist an artifact"{
            val artifact = Artifact("com.github.icarohs7:depchecker-core")
            val dao = ArtifactDao.getInMemoryDatabaseInstance()
            dao.insert(artifact)
            dao.queryAll() shouldBe listOf(Artifact("com.github.icarohs7:depchecker-core"))
        }
        
        "should remove an artifact" {
            val artifact = Artifact("com.github.icarohs7:depchecker-core")
            val dao = ArtifactDao.getInMemoryDatabaseInstance()
            dao.insert(artifact)
            dao.remove(artifact)
            dao.queryAll().forEach(::println)
            dao.queryAll() shouldBe emptyList()
        }
        
        "should erase the database" {
            val artifacts = listOf(
                Artifact("com.github.icarohs7:depchecker-core"),
                Artifact("com.github.icarohs7:UNoxJCommons"),
                Artifact("com.github.icarohs7:UNoxKCommons")
            )
            val dao = ArtifactDao.getInMemoryDatabaseInstance()
            artifacts.forEach(dao::insert)
            dao.removeAll()
            dao.queryAll() shouldBe emptyList()
        }
        
        "should query all artifacts" {
            val listOfArtifacts = listOf(
                Artifact("com.github.icarohs7:depchecker-core"),
                Artifact("com.github.icarohs7:UNoxJCommons"),
                Artifact("com.github.icarohs7:UNoxKCommons")
            )
            val dao = ArtifactDao.getInMemoryDatabaseInstance()
            listOfArtifacts.forEach(dao::insert)
            val listStored = dao.queryAll()
            listStored[0] shouldBe Artifact("com.github.icarohs7:depchecker-core")
            listStored[1] shouldBe Artifact("com.github.icarohs7:UNoxJCommons")
            listStored[2] shouldBe Artifact("com.github.icarohs7:UNoxKCommons")
            listStored.size shouldBe 3
        }
    }
}