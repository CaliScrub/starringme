package org.projectsrk.protoapp;

import org.projectsrk.common.*;
import org.projectsrk.datalayer.*;
import org.projectsrk.webservices.*;

public class DataProvider {
	private static MockData _m = new MockData();
	private static String _providerType = "mock";
	
	public static IStuff GetStuffProvider() {
		if (_providerType.equals("mock")) {
			if (_m == null) {
				_m = new MockData();
			}
			return _m;
		} else if (_providerType.equals("appengine")) {
			return new SrkGaeService();
		} else {
			return null;
		}
	}
	
	public static IStuff GetStuffProvider(String provType) {
		_providerType = provType;
		return GetStuffProvider();
	}
	
	public static void SetProviderType(String provType) {
		_providerType = provType;
	}
}
