`timescale 1 ns/1 ns

module pc(I_ADDR, CLK, PC_EN, LOAD_EN, O_ADDR);
	input CLK, PC_EN, LOAD_EN;
	input [4:0]  I_ADDR;
	output [4:0] O_ADDR; 
	reg [4:0] O_ADDR; 		

	always @(posedge CLK)				
		begin
			if(PC_EN) // If reset is high and enable is high
			begin
				if (LOAD_EN) // If load is 1, load new data
					O_ADDR <= I_ADDR;
				else
					O_ADDR = O_ADDR + 5'b1; 	
			end		
			else // If the  PC_EN = 0					
				O_ADDR = O_ADDR;			
		end
endmodule

