

#import matplotlib.pyplot as plt ##
import numpy


def equations(file_to_read):
    """
    solves systems of linear equations.
    example content of file_to_read:
    3
    1 2 3 7
    -1 2 4 6
    2 1 1 13

    line 0 is number of equations to solve, line 1+ are the equations.
    :param str file to read
    """

    line_count = 0
    equation_list = []
    results = []
    file_to_read = open(file_to_read, "r")
    for line in file_to_read:
        if line_count == 0:
            number_of_eq = int(line)
            line_count += 1
            continue
        if line_count <= number_of_eq:
            line = line.strip("\n").split(" ")
            equations = [int(number) for number in line]
            result = equations.pop()
            results.append(result)
            np_eq = numpy.asarray(equations)
            equation_list.append(np_eq)
            line_count += 1

    a = numpy.matrix([i for i in equation_list])
    b = numpy.array([i for i in results])
    W = numpy.linalg.det(a)

    if W != 0:
        solution = numpy.linalg.solve(a, b)
        print("UKLAD OZNACZONY: ", solution)
    else:
        print("UKŁAD NIEOZNACZONY LUB SPRZRCZNY")


def narcissistic_numbers():
    """
    Function finds narcissistic numbers.
    needs to be canceled or it will run until computer dies for some reason.
    """

    number = 0
    while True:
        square = len(str(number))
        number_list = [int(number) for number in str(number)]

        squared_numbers = [number**square for number in number_list]
        result = sum(squared_numbers)
        if result == number:
            print(number)
        number +=1


def palindrome(n):
    """
    checks if a number is a plindrome, if not
    adds reversed number until it gets a palindrome.
    skips 196 because what the hell.
    :param int n - range
    """

    for i in range (1,n +1):
        results = [i]

        if str(i) == str(i)[::-1] or i == 196:
            continue

        while str(i) != str(i)[::-1]:
            b = str(i)[::-1]
            i = int(i) + int(b)
            results.append(i)

        print(results)


def eratosthenes_sieve(n):

    """
    Finds and print prime numbers in range 1 to n
    :param int range in which function will look for prime numbers.
    """

    prime_numbers = []

    in_range=[True for i in range(n +1)]
    in_range[0:1] = [False, False]

    for i in range(2, n + 1 ):
      if in_range[i]:
          for i in range(2 * i, n+1, i):
              in_range[i] = False

    for i in range(2, n + 1):
      if in_range[i]:
        prime_numbers.append(i)

    print(prime_numbers)


def word_count(text, minimal_word_len=5, top_results=20):
    """
    Method analyses the text and creates a dict containing words and word count.
    :param str text: file with text to read
    :param int minimal_word_len: add minimal word length - default 5 chars
    :param int top_results: how many top results should be printed - default 20
    """

    word_counter = {}
    file = open(text, "r",encoding='utf-8')
    for line in file:
        line = line.strip("\n").lower()
        for word in line.split():
            word = word.strip(",.:;'?!\"\'")
            if len(word) >= minimal_word_len:
                word_counter.setdefault(word,0)
                word_counter[word]+=1

    file.close()

    l2 = sorted(word_counter.items(), key=lambda val: val[1], reverse = True)

    for word,count in l2[:top_results]:
        print(word, ": ", count)




# alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
# zdanie =  "PROGRAMOWANIE"
# zdanie2 = "SSQGXDNQWGQJG"
# klucz = "31206"

def encrypt_gro(text, alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ", key="31206" ):

    """
    method uses Gronsfeld method for string encryption.
    :param text str to encrypt
    :param alphabet str, alphabet to use , default=ABCDEFGHIJKLMNOPQRSTUVWXYZ
    :param key str, key used for encryption, default 31206
    :return str encrypted value.
    """

    result = ""
    key_len = len(key)
    count = 0

    for letter in text:
        count += 1
        alphabet_idx = alphabet.index(letter)

        key_idx = int(key[count % key_len - 1])


        encrypting_idx = int(alphabet_idx + key_idx)

        if encrypting_idx >= len(alphabet):
            diff = (len(alphabet) - alphabet_idx)
            encrypting_idx = key_idx - diff

        encrypting_letter  = alphabet[encrypting_idx]
        result += encrypting_letter

    return result




def decrypt_gro(text, alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", key = "31206"):
    """
    method decrypts strings encrypted with encrypt_gro method
    :param text str to decrypt
    :param alphabet str, alphabet to use , default=ABCDEFGHIJKLMNOPQRSTUVWXYZ
    :param key str, key used for encryption, default 31206
    :return str decrypted value.
    """

    result = ""
    key_len = len(key)
    count = 0

    for letter in text:
        count += 1
        alphabet_idx = alphabet.index(letter)

        key_idx = int(key[count % key_len - 1])

        encrypting_idx = int(alphabet_idx - key_idx)

        if (encrypting_idx < 0):
            encrypting_idx = encrypting_idx + len(alphabet)

        encrypting_letter  = alphabet[encrypting_idx]
        result += encrypting_letter

    return result


if __name__ == "__main__":
    pass
