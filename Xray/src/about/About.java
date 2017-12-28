package about;

import xray.Xray;

/**
 * The "About section" class of the app. Contains data about developer, version
 * and license and more.
 * 
 * @author PilzHere
 *
 */
public class About {

	private String aboutVersion, aboutDeveloper, aboutDate, aboutLicense;

	/**
	 * Sets data to class variables. Such as version, developer and license.
	 */
	public void setAboutData() {
		setAboutVersion(Xray.getVersion());
		setAboutDeveloper(Xray.getDeveloper());
		setAboutDate("2017-12-28");
		String year = getAboutDate().substring(0, 3);

		setAboutLicense("The MIT License (MIT)" + "\n" + "\n" + "Copyright (c) " + year + " " + getAboutDeveloper()
				+ "\n" + "\n" + "Permission is hereby granted, free of charge, to any person obtaining a copy" + "\n"
				+ "of this software and associated documentation files (the " + "\"Software\"" + "), to deal" + "\n"
				+ "in the Software without restriction, including without limitation the rights" + "\n"
				+ "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell" + "\n"
				+ "copies of the Software, and to permit persons to whom the Software is" + "\n"
				+ "furnished to do so, subject to the following conditions:" + "\n" + "\n"
				+ "The above copyright notice and this permission notice shall be included in" + "\n"
				+ "all copies or substantial portions of the Software." + "\n" + "\n" + "THE SOFTWARE IS PROVIDED"
				+ "\"AS IS\"" + ", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR" + "\n"
				+ "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY," + "\n"
				+ "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE" + "\n"
				+ "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER" + "\n"
				+ "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM," + "\n"
				+ "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN" + "\n" + "THE SOFTWARE.");
	}

	public String getAboutVersion() {
		return aboutVersion;
	}

	private void setAboutVersion(String aboutVersion) {
		this.aboutVersion = aboutVersion;
	}

	public String getAboutDeveloper() {
		return aboutDeveloper;
	}

	private void setAboutDeveloper(String aboutDeveloper) {
		this.aboutDeveloper = aboutDeveloper;
	}

	public String getAboutDate() {
		return aboutDate;
	}

	private void setAboutDate(String aboutDate) {
		this.aboutDate = aboutDate;
	}

	public String getAboutLicense() {
		return aboutLicense;
	}

	private void setAboutLicense(String aboutLicense) {
		this.aboutLicense = aboutLicense;
	}
}
