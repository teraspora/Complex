/**
* This class implements a
* range of functipns of Complex numbers.*
* @author  John Lynch
* @version 1.0
* @since   2018-09-20 
*/

/**
* © 2018 John Lynch
* Contact: E: johnlynch.32768 then the word "at" then gmail.com.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

See <https://www.gnu.org/licenses/>.
*/

import java.util.Objects;
import static java.lang.Math.*;

public class Complex {
	
	public double x, y;	// real and imaginary parts respectively; implemented as fields for ease
						// of use and readability in complex Complex functions.
	
	// CONSTANTS: -
	public static final Complex ZERO = new Complex(0.0, 0.0);
	public static final Complex ONE = new Complex(1.0, 0.0);
	public static final Complex TWO = new Complex(2.0, 0.0);
	public static final Complex MINUS_ONE = new Complex(-1.0, 0.0);
	public static final Complex I = new Complex(0.0, 1.0);
	public static final Complex ONE_PLUS_I = new Complex(1.0, 1.0);
	public static final Complex MIN_VALUE = new Complex(Double.MIN_VALUE, 0.0);
	// ========================================================================

	// CONSTRUCTORS: -
	public Complex() {
		this.x = 0.0;
		this.y = 0.0;
	}

	public Complex(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Complex(double x) {
	// convenience constructor to make a complex number of which the supplied argument is the real part
	// and the imaginary part is zero.
		this.x = x;
		this.y = 0.0;
	}

	public Complex(Complex w) {
	// alternative to w.clone(); note, clone() calls this constructor so this way is cheaper.
		this.x = w.x;
		this.y = w.y;
	}
	// ========================================================================

	// METHODS: -

	// FUNDAMENTAL METHODS:
	public String toString() {
		return "" + this.x + " + " + this.y + "i";
	}
	
	public Complex clone() {
		return new Complex(this);
	}

	public boolean equals(Complex w) {
		return w == null ? false : this.x == w.x && this.y == w.y;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return  true; 
		if (!(o instanceof Complex)) return false;
		Complex w = (Complex)o;
		return this.x == w.x && this.y == w.y;
	}

	@Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
	
	public boolean equals(double x) {
		return this.x == x && this.y == 0.0;
	}
	
	public static Complex polar(double r, double theta) {
	// Return a Complex number with the given magnitude and angle.
		return new Complex(r * Math.cos(theta), r * Math.sin(theta));
	}

	// ARITHMETIC METHODS:
	// Note: some overloaded for convenience to take 3 or 4 arguments, and allow real arguments.
	public static Complex sum(Complex z, Complex w) {
		return new Complex(z.x + w.x, z.y + w.y);
	}

	public static Complex sum(Complex z, Complex w, Complex v) {
		return sum(sum(z, w), v);
	}

	public static Complex sum(Complex z, double s) {
		return new Complex(z.x + s, z.y);
	}
	
	public static Complex sum(Complex z, Complex w, double s) {
		return sum(sum(z, w), s);
	}
	
	public static Complex sum(Complex z, double s, double t) {
		return sum(sum(z, s), t);
	}
	
	public static Complex sum(Complex z, Complex w, Complex v, Complex s) {
		return sum(sum(z, w), sum(v, s));
	}
	
	public static Complex difference(Complex z, Complex w) {
		return new Complex(z.x - w.x, z.y - w.y);
	}

	public static Complex product(Complex z, Complex w) {
		return new Complex(z.x * w.x - z.y * w.y, z.x * w.y + z.y * w.x);
	}

	public static Complex product(Complex z, Complex w, Complex v) {
		return product(product(z, w), v);
	}
	
	public static Complex product(Complex z, double s) {
		return new Complex(z.x * s, z.y * s);
	}
	
	public static Complex product(Complex z, Complex w, double s) {
		return product(product(z, w), s);
	}
	
	public static Complex quotient(Complex z, Complex w) {
		return Complex.product(z, w.recip());
	}

	public Complex plus(Complex w) {
		return Complex.sum(this, w);
	}

	public Complex minus(Complex w) {
		return Complex.difference(this, w);
	}

	public Complex times(Complex w) {
		return Complex.product(this, w);
	}

	public Complex over(Complex w) {
		return Complex.quotient(this, w);
	}

	public Complex over(double s) {
		if (s == 0.0) return Complex.MIN_VALUE;
		return new Complex(this.x / s, this.y / s);
	}

	public Complex plus(double s) {
		return Complex.sum(this, s);
	}

	public Complex minus(double s) {
		return Complex.sum(this, -s);
	}

	public Complex times(double s) {
		return Complex.product(this, s);
	}

	public Complex recip() {
		double denom = this.x * this.x + this.y * this.y;
		return denom != 0.0
			? new Complex(this.x / denom, -this.y / denom) 
			: new Complex(Double.MAX_VALUE, Double.MAX_VALUE);
			// You may not like this default but I'd prefer not to return infinity.
	}

	public Complex squared() {
		return new Complex(this.x * this.x - this.y * this.y, 2.0 * this.x * this.y);
	}

	public Complex cubed() {
		return Complex.product(this, this.squared());
	}

	public Complex neg() {
		return new Complex(-this.x, -this.y);
	}

	public Complex pow(int n) {
		if (n > 0) {
			switch(n) {
				case 2:		
				// most common case so hoist it and inline the computation;
					return new Complex(this.x * this.x - this.y * this.y, 2.0 * this.x * this.y);			
				case 0:
					return ONE;
				case 1:
					return this;
				default:			
					return (n % 2 == 0) ? this.pow(n / 2).squared() : product(this.pow(n-1), this);
			}
		}
		return this.pow(-n).recip();
	}
	
	public Complex pow(double p) {
		return (Complex.I.times(p * this.arg())).exp().times(Math.pow(this.mod(), p));
	}

	public Complex pow(Complex w) {
		return product(this.ln(), w).exp();
	}

	public Complex sqrt() {
		double rootR = Math.sqrt(this.mod());
		double thetaOver2 = this.arg() / 2.0;
		return new Complex(rootR * Math.cos(thetaOver2), rootR * Math.sin(thetaOver2));
	}
	
	public Complex sqrtAlg() {
	// allgebraic method, to compare results and performance...
		double zAbs = this.mod();
		double v = Math.sqrt((zAbs - this.x) / 2.0);
		return new Complex(Math.sqrt((zAbs + this.x) / 2.0), y < 0 ? -v : v);		
	}
	
	// AVERAGING METHODS:
	public static Complex mean(Complex z, Complex w) {
		return new Complex((z.x + w.x) / 2.0, (z.y + w.y) / 2.0);
	}

	public static Complex geoMean(Complex z, Complex w) {
		return new Complex(Math.sqrt(z.x * w.x), Math.sqrt(z.y * w.y));
	}

	public Complex geoMean(Complex w) {
		return new Complex(Math.sqrt(this.x * w.x), Math.sqrt(this.y * w.y));
	}

	// USEFUL UNARY OPERATORS:
	public Complex bar() {	
	// Return the complex conjugate of the argument
		return new Complex(this.x, -this.y);
	}

	public Complex absRealAndImag() {
	// make both components positive;
		return new Complex(Math.abs(this.x), Math.abs(this.y));
	}
	
	public Complex flipXY() {
		return new Complex(this.y, this.x);
	}

	public double mod() {
	// Return the modulus of the argument	
			return Math.hypot(this.x, this.y);
	}

	public double abs() {		
	// just another name for modulus, or the magnitude of the vector
		return this.mod();
	}
	
	public double arg() {		
	// the angle between the vector and the x-axis.
		return atan2(this.y, this.x);
	}

	// EXPONENTIAL, LOGARITHMIC & TRIGONOMETRIC FUNCTIONS
	public Complex exp() {
		return product(new Complex(Math.cos(this.y), Math.sin(this.y)), Math.exp(this.x));
	}
	
	public Complex ln() {
		return new Complex(Math.log(this.mod()), this.arg());
	}

	public Complex sin() {
		return new Complex(Math.sin(this.x) * Math.cosh(this.y), Math.cos(this.x) * Math.sinh(this.y));
	}

	public Complex cos() {
		return new Complex(Math.cos(this.x) * Math.cosh(this.y), -Math.sin(this.x) * Math.sinh(this.y));
	}

	public Complex tan() {
			return this.sin().over(this.cos());
	}

	public Complex sinh() {
		return new Complex(Math.sinh(this.x) * Math.cos(this.y), Math.cosh(this.x) * Math.sin(this.y));
	}
		
	public Complex cosh() {
		return new Complex(Math.cosh(this.x) * Math.cos(this.y), Math.sinh(this.x) * Math.sin(this.y));
	}
	 
	public Complex tanh() {
		return this.sinh().over(this.cosh());
	}

	public Complex sinPlusCos() {
		return this.sin().plus(this.cos());
	}

	// COMPARISON METHODS:
	public int compareTo (Complex w) {
	// compare the magnitudes
		return Double.compare(this.mod(), w.mod());
	}
	public boolean compare (Complex w) {
		return this.compareTo(w) >= 0;
	}
	public static Complex max(Complex z, Complex w) {
		return (z.compareTo(w) > 0) ? z : w;
	}
	public static Complex min(Complex z, Complex w) {
		return (z.compareTo(w) < 0) ? z : w;
	}

	public boolean isInSameQuadrantAs(Complex w) {
		return Math.signum(this.x) == Math.signum(w.x) && Math.signum(this.y) == Math.signum(w.y);
	}

	// VECTOR METHODS:
	public Complex hat(Complex w) {
	//return the unit vector in the direction of w;
		return w.over(w.abs());
	}

	public Complex normalize(Complex w) {
	//return the unit vector in the direction of w; same as hat();
		return w.over(w.abs());
	}

	public Complex rotate(double phi) {
		return this.times(polar(1.0, phi));
	}

	public double dot(Complex w) {		// vector dot product
		return this.x * w.x + this.y * w.y;
	}
	
	public double angleBetween(Complex w) {
		return this.arg() - w.arg();
	}
	
	public double cross3dMagnitude(Complex w) {		
	// the magnitude of the cross product of the vectors treated as 3d vectors in the xy plane
	// when the Complex plane is imagined as being embedded in 3d Euclidean space;
	// we therefore set the z-component to zero and so the x- and y- components of the cross product are zero
	// and the z-component is the magnitude of the cross product.
	// NOTE:
	// we could also multiply this result by the magnitudes of the original vectors and the sine of the angle
	// between them to get the volume of a parallelepiped of which the three vectors are coincident edges;
	// note that this paralelepiped is not general, in the sense that one vertex is always at the origin,
	// one edge extends along the z-axis from here and four edges lie in the xy plane; or, to
	// imagine it another way, eight of the twelve edges have no z-component and four have ONLY a z- component.
		return this.x * w.y + this.y * w.x; 
	}	

	// UTILITY METHODS:

	public double x2() {		// shorthand, or syntactic sugar for Re(z) squared
		return this.x * this.x;
	}

	public double y2() {		// shorthand, or syntactic sugar for Imag(z) squared
		return this.y * this.y;
	}

	public double mod2() {		
	// shorthand, or syntactic sugar for the square of the modulus,
	// as we often don't need the expensive square root operation as we are, for example, just 
	// comparing two moduli so we might just as well compare the squares since moduli are always positive.
		return this.x * this.x + this.y * this.y;
	}
	
	// STRANGE METHODS: :)
	// These are for generating Mandelbox-type fractals.
	public Complex boxFold(double fold) {
		return new Complex(
			this.x > fold ? 2.0 * fold - this.x : (this.x < -fold ? -2.0 * fold - this.x : this.x),
			this.y > fold ? 2.0 * fold - this.y : (this.y < -fold ? -2.0 * fold - this.y : this.y));
	}

	public Complex ballFold(double r, double bigR) {
		double zAbs = this.mod();
		r = Math.abs(r);
		return zAbs < r ? this.over(r * r)	: (zAbs < Math.abs(bigR) ? this.over(zAbs * zAbs) : this);		// ballfold
	}

}	// end class Complex
