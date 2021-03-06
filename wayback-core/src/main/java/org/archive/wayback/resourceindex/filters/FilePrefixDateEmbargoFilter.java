/*
 *  This file is part of the Wayback archival access software
 *   (http://archive-access.sourceforge.net/projects/wayback/).
 *
 *  Licensed to the Internet Archive (IA) by one or more individual 
 *  contributors. 
 *
 *  The IA licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.archive.wayback.resourceindex.filters;

import java.util.Date;

import org.archive.wayback.core.CaptureSearchResult;
import org.archive.wayback.util.ObjectFilter;

/**
 * Blocks only files matching a given prefix, iff they are newer than a given
 * embargo period.
 * 
 * @author brad
 *
 */
public class FilePrefixDateEmbargoFilter  implements ObjectFilter<CaptureSearchResult> {
	protected String matchPrefix = null;
	protected Date embargoDate = null;
	public FilePrefixDateEmbargoFilter(String prefix, long minAge) {
		matchPrefix = prefix;
		embargoDate = new Date(System.currentTimeMillis() - minAge);
	}
	public int filterObject(CaptureSearchResult o) {
		if(!o.getFile().startsWith(matchPrefix)) {
			return FILTER_INCLUDE;
		}
		return o.getCaptureDate().compareTo(embargoDate) < 0 
			? FILTER_INCLUDE : FILTER_EXCLUDE;
	}
	
}
