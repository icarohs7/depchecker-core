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
import org.junit.Before
import org.junit.Test
import se.lovef.assert.v1.shouldEqual

class ArtifactRamDatabaseTest {

    @Before
    fun setUp() {
        ArtifactDao.getInMemoryDatabaseInstance().removeAll()
    }

    @Test
    fun `should persist an artifact`() {
        //Arrange
        val artifact = Artifact("com.github.icarohs7:depchecker-core")
        val dao = ArtifactDao.getInMemoryDatabaseInstance()

        //Act
        dao.insert(artifact)

        //Assert
        dao.queryAll() shouldEqual listOf(Artifact("com.github.icarohs7:depchecker-core"))
    }

    @Test
    fun `should remove an artifact`() {
        //Arrange
        val artifact = Artifact("com.github.icarohs7:depchecker-core")
        val dao = ArtifactDao.getInMemoryDatabaseInstance()

        //Act
        dao.insert(artifact)
        dao.remove(artifact)

        //Assert
        dao.queryAll() shouldEqual emptyList()
    }

    @Test
    fun `should erase the database`() {
        //Arrange
        val artifacts = listOf(
                Artifact("com.github.icarohs7:depchecker-core"),
                Artifact("com.github.icarohs7:UNoxJCommons"),
                Artifact("com.github.icarohs7:UNoxKCommons")
        )
        val dao = ArtifactDao.getInMemoryDatabaseInstance()

        //Act
        artifacts.forEach(dao::insert)
        dao.removeAll()

        //Assert
        dao.queryAll() shouldEqual emptyList()
    }

    @Test
    fun `should query all artifacts`() {
        //Arrange
        val listOfArtifacts = listOf(
                Artifact("com.github.icarohs7:depchecker-core"),
                Artifact("com.github.icarohs7:UNoxJCommons"),
                Artifact("com.github.icarohs7:UNoxKCommons")
        )
        val dao = ArtifactDao.getInMemoryDatabaseInstance()

        //Act && Arrange more
        listOfArtifacts.forEach(dao::insert)
        val listStored = dao.queryAll()

        //Assert
        listStored[0] shouldEqual Artifact("com.github.icarohs7:depchecker-core")
        listStored[1] shouldEqual Artifact("com.github.icarohs7:UNoxJCommons")
        listStored[2] shouldEqual Artifact("com.github.icarohs7:UNoxKCommons")
        listStored.size shouldEqual 3
    }
}