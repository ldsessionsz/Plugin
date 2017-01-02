package org.ldsessions.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_11_R1.EnumParticle;

public class ParticleTypes {

	private EnumParticle particle1;
	private EnumParticle particle2;
	private EnumParticle particle3;
	private EnumParticle particle4;
	private int lenth;

	public ParticleTypes(EnumParticle particle1) {
		setParticle1(particle1);
		setLenth(1);
	}

	public ParticleTypes(EnumParticle particle1, EnumParticle particle2) {
		setParticle1(particle1);
		setParticle2(particle2);
		setLenth(2);
	}

	public ParticleTypes(EnumParticle particle1, EnumParticle particle2, EnumParticle particle3) {
		setParticle1(particle1);
		setParticle2(particle2);
		setParticle3(particle3);
		setLenth(3);
	}

	public ParticleTypes(EnumParticle particle1, EnumParticle particle2, EnumParticle particle3,
			EnumParticle particle4) {
		setParticle1(particle1);
		setParticle2(particle2);
		setParticle3(particle3);
		setParticle4(particle4);
		setLenth(4);
	}

	public EnumParticle getParticle1() {
		return particle1;
	}

	public void setParticle1(EnumParticle particle1) {
		this.particle1 = particle1;
	}

	public EnumParticle getParticle2() {
		return particle2;
	}

	public void setParticle2(EnumParticle particle2) {
		this.particle2 = particle2;
	}

	public EnumParticle getParticle3() {
		return particle3;
	}

	public void setParticle3(EnumParticle particle3) {
		this.particle3 = particle3;
	}

	public EnumParticle getParticle4() {
		return particle4;
	}

	public void setParticle4(EnumParticle particle4) {
		this.particle4 = particle4;
	}

	public int getLenth() {
		return lenth;
	}

	private void setLenth(int lenth) {
		this.lenth = lenth;
	}

	public List<EnumParticle> getList() {
		List<EnumParticle> list = new ArrayList<EnumParticle>();
		switch (getLenth()) {
		case 1:
			list.add(getParticle1());
			break;
		case 2:
			list.add(getParticle1());
			list.add(getParticle2());
			break;
		case 3:
			list.add(getParticle1());
			list.add(getParticle2());
			list.add(getParticle3());
			break;
		case 4:
			list.add(getParticle1());
			list.add(getParticle2());
			list.add(getParticle3());
			list.add(getParticle4());
			break;
		default:
			new Exception("No particle types to add!");
		}
		return list;
	}

}
