package sionois.terraduellum;

public class ModRef 
{
	public static final String ModID = "terraduellum";
	public static final String ModName = "TerraDuellum";

	public static final int VersionMajor = 1;
	public static final int VersionMinor = 6;
	public static final int VersionRevision = 0;

	public static final String ModVersion = VersionMajor + "." + VersionMinor + "." + VersionRevision;
	
	public static final String ModDependencies = "required-after:terrafirmacraft";
	
	public static final String SERVER_PROXY_CLASS = "sionois.terraduellum.CommonProxy";
	public static final String CLIENT_PROXY_CLASS = "sionois.terraduellum.ClientProxy";
}
