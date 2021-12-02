module Exercises.Day1.Part1 (
  executePart1
) where


convertToInt :: String -> Int
convertToInt a = read a :: Int

getIntLines :: String -> [Int]
getIntLines = map convertToInt . lines


executePart1 :: IO ()
executePart1 = do raw <- readFileContent
                  mapM_ print (getIntLines raw)

readFileContent :: IO String
readFileContent = readFile "src/main/resources/day1/input"
