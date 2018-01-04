`timescale 1ns/100ps
module ALU(CLK, EN, OE, OPCODE, A, B, OUT, OF, SF, ZF, CF);
	parameter WIDTH = 8;
	
	output reg [WIDTH - 1:0] OUT;
	output reg OF, SF, ZF, CF;
	input [3:0] OPCODE;
	input [WIDTH - 1:0] A, B;
	input CLK, EN, OE;
	
	reg [WIDTH - 1: 0] ALU_OUT;
	

	
	localparam A_ADD_B   = 4'b0010;
	localparam A_MINUS_B = 4'b0011;
	localparam A_AND_B   = 4'b0100;
	localparam A_OR_B    = 4'b0101;
	localparam A_XOR_B   = 4'b0110;
	localparam A_NOT     = 4'b0111;
	
	// Feels inefficient to calculate overflow and carry out but couldn't think of the efficient way
	reg [WIDTH :0] temp;
	
	// Fixed from lab8 to make OE and EN independent
    assign OUT = (OE) ? ALU_OUT : 8'bz;
	
	always @(posedge CLK)
	begin	
			if(EN)
			begin 
				case (OPCODE)
				A_ADD_B  : begin
							ALU_OUT = A + B;
							temp = {1'b0,A} + {1'b0,B}; // Concatenated to add two 9-bits numbers and get the correct Carry Flag
							if(A[WIDTH - 1] == B[WIDTH - 1])
								begin
									if(temp[WIDTH - 1] != A[WIDTH - 1])
										OF = 1'b1;
									else
										OF = 1'b0;
								end
							else
								OF = 1'b0;
							
							SF = ALU_OUT[7];
							ZF = ~|(ALU_OUT);
							
							if(temp[WIDTH] == 1'b1 && (A[WIDTH-1] || B[WIDTH-1]))
								CF = 1'b1;
							else
								CF = 1'b0; 
							end
				A_MINUS_B: begin
							ALU_OUT = A + ~B + 1'b1; //Subtraction with Two's Complement
							temp = {1'b0, A} + {1'b0, ~B} + 1'b1;
							if(A[WIDTH - 1] == B[WIDTH - 1])
								begin
									if(temp[WIDTH - 1] != A[WIDTH - 1])
										OF = 1'b1;
									else
										OF = 1'b0;
								end
							else
								OF = 1'b0;	
							SF = ALU_OUT[7];
							ZF = ~|(ALU_OUT);
							
							// Don't know why the < only works when I concatenate an extra 0 to both A and B
							if({1'b0,A} < {1'b0,B})
								CF = 1'b1;
							else
								CF = 1'b0;
							
							end
				A_AND_B  : begin
							ALU_OUT = A & B;	
							SF = ALU_OUT[7];
							ZF = ~|(ALU_OUT);
							end
				A_OR_B   : begin
							ALU_OUT = A | B;
							SF = ALU_OUT[7];
							ZF = ~|(ALU_OUT);
							end
				A_XOR_B  : begin 
							ALU_OUT = A ^ B;
							SF = ALU_OUT[7];
							ZF = ~|(ALU_OUT);
							end
				A_NOT    : begin 
							ALU_OUT = ~A;	
							SF = ALU_OUT[7];
							ZF = ~|(ALU_OUT);
							end
				endcase // any other case will not change the ALU_OUT
				
				
			end //left out else - statement to create latch to maintain its previous state
	end


	
endmodule 