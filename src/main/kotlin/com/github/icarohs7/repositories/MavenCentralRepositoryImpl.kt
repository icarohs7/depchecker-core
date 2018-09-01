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

package com.github.icarohs7.repositories

import com.beust.klaxon.JsonObject
import com.github.icarohs7.entities.*
import com.github.icarohs7.integration.*

object MavenCentralRepositoryImpl : DcRepository {
    override fun getAllVersions(artifact: Artifact): List<String> {
        return getArtifactMetadata(artifact)
            ?.obj("response")
            ?.array<JsonObject>("docs")
            ?.mapNotNull { it.string("v") }
            ?: emptyList()
    }
    
    override fun getLastVersion(artifact: Artifact): String? {
        return getAllVersions(artifact).firstOrNull()
    }
    
    override fun getApiUrl(artifact: Artifact): String {
        val (group, artifactId) = artifact
        return """
            https://search.maven.org
            /solrsearch
            /select?q=g:"$group"+AND+a:"$artifactId"&core=gav&rows=20&wt=json
            """.trimIndent().replace("\n", "")
    }
    
    private fun getArtifactMetadata(artifact: Artifact): JsonObject? {
        val response = MavenCentralRepositoryImpl.getApiUrl(artifact).httpGetResponse()
        return if (response.successful) {
            response.body.toJsonObject()
        } else {
            null
        }
    }
}