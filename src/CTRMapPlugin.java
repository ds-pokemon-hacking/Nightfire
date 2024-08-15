
import ctrmap.CTRMapVResources;
import ctrmap.Launc;
import ctrmap.editor.CTRMapMenuActions;
import ctrmap.editor.gui.editors.gen5.battle.encounters.VWildEditor;
import ctrmap.editor.gui.editors.gen5.battle.trainer.VTrainerEditor;
import ctrmap.editor.gui.editors.gen5.level.maps.VMapContainerEditor;
import ctrmap.editor.gui.editors.gen5.level.maps.VZoneMatrixEditor;
import ctrmap.editor.gui.editors.gen5.level.VLevelEditor;
import ctrmap.editor.gui.editors.gen5.level.VZoneEditor;
import ctrmap.editor.gui.editors.gen5.level.building.VPropEditor;
import ctrmap.editor.gui.editors.gen5.level.camera.VCameraEditor;
import ctrmap.editor.gui.editors.gen5.level.entities.VEventEditor;
import ctrmap.editor.gui.editors.gen5.level.entities.VProxyEditor;
import ctrmap.editor.gui.editors.gen5.level.entities.VNPCEditor;
import ctrmap.editor.gui.editors.gen5.level.entities.VScriptingAssistant;
import ctrmap.editor.gui.editors.gen5.level.entities.VTriggerEditor;
import ctrmap.editor.gui.editors.gen5.level.entities.VWarpEditor;
import ctrmap.editor.gui.editors.gen5.level.extra.VExtrasPanel;
import ctrmap.editor.gui.editors.gen5.level.rail.VRailEditor;
import ctrmap.editor.gui.editors.scenegraph.ScenegraphExplorer;
import ctrmap.editor.gui.workspace.ROMExportDialog;
import ctrmap.editor.system.juliet.CTRMapPluginInterface;
import ctrmap.editor.system.juliet.ICTRMapPlugin;
import ctrmap.editor.system.workspace.CTRMapProject;
import ctrmap.formats.common.GameInfo;
import ctrmap.formats.ntr.rom.srl.NDSROM;
import ctrmap.util.tools.cont.ContainerUtil;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import rtldr.JRTLDRCore;
import xstandard.fs.FSFile;
import xstandard.fs.VFSFile;
import xstandard.fs.accessors.DiskFile;
import xstandard.fs.accessors.MemoryFile;
import xstandard.fs.accessors.ProxyFile;
import xstandard.fs.accessors.arc.ArcFile;
import xstandard.fs.accessors.arc.ArcInput;
import xstandard.gui.DialogUtils;

public class CTRMapPlugin implements ICTRMapPlugin {

	public CTRMapPlugin() {
		System.out.println("Loading Nightfire...");
		CTRMapVResources.load();
	}

	public static void main(String[] args) {
		JRTLDRCore.suppressDebugPluginByFileName("Nightfire.jar");
		JRTLDRCore.addDebugSelfClassLoader(CTRMapPlugin.class.getProtectionDomain().getCodeSource());
		Launc.main(null);
	}

	@Override
	public void registPerspectives(CTRMapPluginInterface j) {
		j.rmoRegistPerspective(VLevelEditor.class);
	}

	@Override
	public void registEditors(CTRMapPluginInterface j) {
		j.rmoRegistTabbedEditors(
                    VLevelEditor.class,
                    VTrainerEditor.class,
                    VWildEditor.class,
                    VZoneMatrixEditor.class,
                    VMapContainerEditor.class
		);
	}

	@Override
	public void registUI(CTRMapPluginInterface j, GameInfo game) {
		if (game.isGenV()) {
			j.rmoAddAboutDialogCredits(
				"PlatinumMaster - Nightfire"
			);
		}
	}
}
